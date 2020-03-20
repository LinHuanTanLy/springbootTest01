package com.ly.shop.controller.user;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.shop.api.CommResult;
import com.ly.shop.api.ErrCode;
import com.ly.shop.base.BaseController;
import com.ly.shop.constant.ShopConstant;
import com.ly.shop.entity.User;
import com.ly.shop.service.UserService;
import com.ly.shop.vo.user.UserLoginVo;
import com.ly.shop.vo.user.UserRegisterVo;
import com.ly.shop.vo.user.UserSelectVo;
import com.ly.shop.vo.user.UserUpdateVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.List;

import static com.ly.shop.constant.ShopConstant.SESSION_KEY_NAME;

/**
 * 思路：
 * 1. login register 接口进行注册
 * 2. suc callback里面拿到user信息，丢进springSession
 * 3. aop切口类 @see com.ly.shop.aop.ControllerAop 进行切口
 * 4. 在@Before里面拿到方法名和session
 * 5. 除开过滤的接口方法，其他接口如果没有session，throw exception
 * 6. @see com.ly.shop.exception.GlobalExceptionHandler 进行异常捕获，统一格式后抛出
 */
@RestController
@Validated
@Api(value = "用户管理")
@RequestMapping("api/user/")
@Slf4j
public class UserController extends BaseController {

    @Autowired
    UserService userService;


    @PostMapping("/login")
    @ApiOperation("登录")
    public CommResult<User> postLogin(HttpSession httpSession, @Validated @RequestBody UserLoginVo userLoginVo) {
        UserSelectVo userSelectVo = new UserSelectVo(userLoginVo.getUserName(), userLoginVo.getPassWord());
        User user = userService.findUser(userSelectVo).get(0);
        httpSession.setAttribute(SESSION_KEY_NAME, user);
        return CommResult.suc(user);
    }


    @GetMapping("/session")
    @ApiOperation("测试方法，获取session")
    public CommResult<User> getSession(HttpSession session) {
        Object o = session.getAttribute(SESSION_KEY_NAME);
        if (o != null) {
            User user = (User) o;
            return CommResult.suc(user);
        } else {
            return CommResult.fail(ErrCode.REQUEST_ERR.getCode(), "没有找到对应的session");
        }
    }

    @PostMapping("/register")
    @ApiOperation("注册")
    public CommResult<User> postRegister(HttpSession httpSession, @Validated @RequestBody UserRegisterVo userRegisterVo) {
        /// 从session 中拿到操作者，
        User operator = getUserFromSession(httpSession);
        User user = new User();
        BeanUtils.copyProperties(userRegisterVo, user);
        /// 补上空缺的字段
        if (operator != null) {
            user.setCreator(operator.getUserName());
            user.setCreatorId(operator.getId());
            user.setLastOperator(operator.getUserName());
            user.setLastOperatorId(Integer.valueOf(operator.getId().toString()));
        }
        user.setCreatedTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setVersion(1);
        int resultRow = userService.addUserInfo(user);
        if (resultRow == 1) {
            User addResultUser = userService.findUser(new UserSelectVo(user.getUserName(), user.getPassWord())).get(0);
            httpSession.setAttribute(SESSION_KEY_NAME, addResultUser);
            return CommResult.suc(user);
        } else {
            return CommResult.fail(ErrCode.REQUEST_ERR.getCode(), "注册失败");
        }
    }


    @PostMapping("/loginOut")
    @ApiOperation("注销")
    public CommResult<Boolean> postLoginOut(HttpSession httpSession) {
        httpSession.removeAttribute(SESSION_KEY_NAME);
        if (httpSession.getAttribute(SESSION_KEY_NAME) == null) {
            return CommResult.suc(true);
        } else {
            return CommResult.fail(false);
        }
    }


    @PostMapping("/find")
    @ApiOperation("根据信息查找用户信息")
    public CommResult<List<User>> postUserInfo(@RequestBody UserSelectVo userSelectVo) {
        return CommResult.suc(userService.findUser(userSelectVo));
    }

    @PutMapping("/update")
    @ApiOperation("更新用户信息")
    public CommResult<Boolean> putUpdateUserInfo(HttpSession httpSession, @RequestBody UserUpdateVo userUpdateVo) {
        User user = getUserFromSession(httpSession);
        if (user != null) {
            userUpdateVo.setUpdateTime(LocalDateTime.now());
            userUpdateVo.setLastOperator(user.getUserName());
            userUpdateVo.setLastOperatorId(user.getLastOperatorId());
        }
        int resultCode = userService.updateUsr(userUpdateVo);
        if (resultCode == 1) {
            return CommResult.suc(true);
        } else {
            return CommResult.fail(ErrCode.UPDATE_ERROR.getCode(), ErrCode.UPDATE_ERROR.getMsg());
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除用户")
    public CommResult<Boolean> deleteUser(HttpSession httpSession, @PathVariable int id) {
        UserUpdateVo userUpdateVo = new UserUpdateVo();
        User user = getUserFromSession(httpSession);
        assignmentUserUpdateInfo(userUpdateVo, user);
        userUpdateVo.setId((long) id);
        userUpdateVo.setIsDeleted(ShopConstant.isDelete.DELETE);
        int resultCode = userService.updateUsr(userUpdateVo);
        if (resultCode == 1) {
            return CommResult.suc(true);
        } else {
            return CommResult.fail(ErrCode.UPDATE_ERROR.getCode(), ErrCode.UPDATE_ERROR.getMsg());
        }
    }

    @GetMapping("/")
    @ApiOperation("列出所有用户")
    public CommResult<PageInfo<User>> users(@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                            @RequestParam(value = "pageNum", defaultValue = "0") int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userService.findUser(new UserSelectVo());
        return CommResult.suc(new PageInfo<>(list));
    }

    /**
     * 赋值更新时间等
     *
     * @param userUpdateVo
     * @param user
     */
    private void assignmentUserUpdateInfo(UserUpdateVo userUpdateVo, User user) {
        if (user != null) {
            userUpdateVo.setUpdateTime(LocalDateTime.now());
            userUpdateVo.setLastOperator(user.getUserName());
            userUpdateVo.setLastOperatorId(user.getLastOperatorId());
        }
    }
}
