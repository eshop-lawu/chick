package com.lawu.chick.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.chick.api.authorization.impl.UserConstant;
import com.lawu.chick.api.converter.ChickenConverter;
import com.lawu.chick.api.dto.ChickFeedReturnDTO;
import com.lawu.chick.framework.web.impl.ResultCode;
import com.lawu.chick.service.ChickenService;
import com.lawu.chick.service.InventoryService;
import com.lawu.chick.service.RangelandChickService;
import com.lawu.chick.service.SysConfigService;
import com.lawu.chick.service.bo.ChickBaseConfigBO;
import com.lawu.chick.service.bo.ChickenBaseInfoBO;
import com.lawu.chick.service.bo.InventoryInfoBO;
import com.lawu.chick.service.exception.ChickFeedProdTimesOutException;
import com.lawu.chick.service.exception.DataNotExistException;
import com.lawu.chick.service.exception.IllegalOperationException;
import com.lawu.chick.service.param.FriendFeedParam;
import com.lawu.chick.service.param.OwnerFeedParam;
import com.lawu.chick.service.query.FriendFeedQueryParam;
import com.lawu.chick.service.query.OwnerFeedQueryParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2018/4/26.
 */
@Api(tags = "chicken")
@RestController
@RequestMapping(value = "chicken/")
public class ChickenController extends BaseController {

    @Autowired
    private ChickenService chickenService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private SysConfigService sysConfigService;
    @Autowired
    private RangelandChickService rangelandChickService;

    @Audit(date = "2018-04-28", reviewer = "孙林青")
    @ApiOperation(value = "修改小鸡名称", notes = "修改小鸡名称（梅述全）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "updateChickenName", method = RequestMethod.PUT)
    public Result updateChickenName(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                       @RequestParam @ApiParam(required = true, value = "小鸡编号") String num,
                                                       @RequestParam @ApiParam(required = true, value = "小鸡名称") String name) {
        chickenService.updateChickenName(num, name);
        return successCreated();
    }

    @Audit(date = "2018-04-28", reviewer = "孙林青")
    @ApiOperation(value = "主人喂养小鸡", notes = "主人喂养小鸡（李洪军）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "ownerFeed", method = RequestMethod.POST)
    public Result<ChickFeedReturnDTO> ownerFeed(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,@ModelAttribute @ApiParam OwnerFeedParam param){
    	String memberNum = UserUtil.getCurrentUserNum(getRequest());
    	OwnerFeedQueryParam queryParam =new OwnerFeedQueryParam();
    	queryParam.setInventoryId(param.getInventoryId());
    	queryParam.setMemberNum(memberNum);
    	queryParam.setNum(param.getNum());
    	InventoryInfoBO invent = inventoryService.getInventoryFeedInfoById(queryParam.getInventoryId());
    	if(null == invent){
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY);
    	}
    	if(!invent.getMemberNum().equals(queryParam.getMemberNum())){
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY);
		}
		if(invent.getQuantity()<=0){
			return successCreated(ResultCode.FEED_INVENTORY_NOT_ENOUGH);
		}
		ChickenBaseInfoBO chickInfo = chickenService.getChickenInfoByMembernumNum(memberNum, param.getNum());

		ChickBaseConfigBO cacheConfig = sysConfigService.getCacheChickBaseInfo();
		if(chickInfo.getFullVal()<cacheConfig.getChickMaxFullVal()){
			try {
				ChickenBaseInfoBO bo = rangelandChickService.ownerFeed(queryParam);
				ChickFeedReturnDTO dto =ChickenConverter.convertChickFeedReturnDTO(bo);
				return successCreated(dto);
			}catch(ChickFeedProdTimesOutException e){
				return successCreated(ResultCode.CHICK_FEED_PROD_TIMES);
			} catch (Exception e) {
				return successCreated(ResultCode.FAIL);
			}
		}else{
			return successCreated(ResultCode.CHICK_IS_FULL);
		}
    }

    @Audit(date = "2018-04-28", reviewer = "孙林青")
    @ApiOperation(value = "好友喂养小鸡", notes = "好友喂养小鸡（李洪军）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "friendFeed", method = RequestMethod.POST)
    public Result<ChickFeedReturnDTO> friendFeed(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,@ModelAttribute @ApiParam FriendFeedParam param){
    	String memberNum = UserUtil.getCurrentUserNum(getRequest());
    	FriendFeedQueryParam queryParam =new FriendFeedQueryParam();
    	queryParam.setFriendNum(param.getFriendNum());
    	queryParam.setInventoryId(param.getInventoryId());
    	queryParam.setMemberNum(memberNum);
    	queryParam.setNum(param.getNum());
    	InventoryInfoBO invent = inventoryService.getInventoryFeedInfoById(queryParam.getInventoryId());
    	if(null == invent){
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY);
    	}
    	if(!invent.getMemberNum().equals(queryParam.getMemberNum())||memberNum.equals(queryParam.getFriendNum())){
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY);
		}
		if(invent.getQuantity()<=0){
			return successCreated(ResultCode.FEED_INVENTORY_NOT_ENOUGH);
		}
		ChickenBaseInfoBO infoBo = chickenService.getChickenInfoByMembernumNum(param.getFriendNum(), param.getNum());
		if(null ==infoBo){
			return successCreated(ResultCode.WRONG_OPERATION);
		}
		ChickenBaseInfoBO chickInfo = chickenService.getChickenInfoByMembernumNum(queryParam.getFriendNum(), param.getNum());
		ChickBaseConfigBO cacheConfig = sysConfigService.getCacheChickBaseInfo();
		if(chickInfo.getFullVal()<cacheConfig.getChickMaxFullVal()){
			try{
				ChickenBaseInfoBO bo = rangelandChickService.friendFeed(queryParam);
				ChickFeedReturnDTO dto =ChickenConverter.convertChickFeedReturnDTO(bo);
				return successCreated(dto);
			}catch(ChickFeedProdTimesOutException e){
				return successCreated(ResultCode.CHICK_FEED_PROD_TIMES);
			}catch(Exception e){
				return successCreated(ResultCode.FAIL);
			}
		}else{
			return successCreated(ResultCode.CHICK_IS_FULL);
		}
		
    }

	@Audit(date = "2018-06-13", reviewer = "孙林青")
    @SuppressWarnings("rawtypes")
    @Authorization
    @ApiOperation(value = "收取鸡蛋", notes = "收取鸡蛋[1104]（蒋鑫俊）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "receiveEggs", method = RequestMethod.PUT)
    public Result receiveEggs(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @RequestParam("chickNum") String chickNum) {
        String memberNum = UserUtil.getCurrentUserNum(getRequest());
        try {
            chickenService.receiveEggs(memberNum, chickNum);
            return successCreated();
        } catch (DataNotExistException e) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND, e.getMessage());
        } catch (IllegalOperationException e) {
            return successCreated(ResultCode.ILLEGAL_OPERATION, e.getMessage());
        }
    }
}
