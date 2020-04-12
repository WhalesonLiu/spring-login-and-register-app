package com.caroline.fruit.service;

import com.caroline.fruit.core.util.FruitUtil;
import com.caroline.fruit.exception.FruitException;
import com.caroline.fruit.exception.FruitMsgEnum;
import com.caroline.fruit.message.Message;
import com.caroline.fruit.message.Result;
import com.caroline.fruit.model.DeliveryInfo;
import com.caroline.fruit.repository.DeliveryInfoRepository;
import com.caroline.fruit.dto.AddUserDto;
import com.caroline.fruit.model.Role;
import com.caroline.fruit.model.User;
import com.caroline.fruit.repository.UserRepository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final DeliveryInfoRepository deliveryInfoRepository;
    private RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private AuthenticationManager authenticationManager;

    private final FruitUtil fruitUtil;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService,
                           @Lazy BCryptPasswordEncoder bCryptPasswordEncoder,
                           @Lazy AuthenticationManager authenticationManager,
                           DeliveryInfoRepository deliveryInfoRepository,
                           FruitUtil fruitUtil) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.deliveryInfoRepository = deliveryInfoRepository;
        this.fruitUtil = fruitUtil;
    }

    @Override
    public User createBasicUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRole("USER"));
        user.setRoles(roles);

        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String email) {
        return userRepository.findByUsername(email);
    }

    @Override
    public Result findByWeChatAccount(String weChatAccount) throws FruitException {
        try{
            Result result = new Result();

            User user = userRepository.findByWeChatAccount(weChatAccount);
            if(user == null){
                throw new FruitException(FruitMsgEnum.NotFoundUser);
            }
            result.setResponseReplyInfo(user);
            return result;
        }catch (Exception e){

            throw new FruitException(FruitMsgEnum.Exception);

        }
    }

    @Override
    public User getLoggedInUser() {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof User) {
            return (User) user;
        }
        return null;
    }

    @Override
    public Result addUser(AddUserDto addUserDto) throws FruitException {

        try {
            Result result = new Result();

            User user = new User();
            user.setId(fruitUtil.getTableId());
            user.setRealName(addUserDto.getRealName());
            user.setGender(addUserDto.getGender());
            user.setWeChatAccount(addUserDto.getWeChatAccount());
            user.setWeChatName(addUserDto.getWeChatName());
            user.setBirthDay(addUserDto.getBirthDay());
            user.setUserRemark(addUserDto.getUserRemark());
            user.setJoinDay(new Date());
            user = userRepository.save(user);
            if(user != null && addUserDto.getDeliveryInfoList() != null){
                User finalUser = user;
                addUserDto.getDeliveryInfoList().forEach(e ->{
                    DeliveryInfo deliveryInfo = new DeliveryInfo();
                    deliveryInfo.setInfoId(FruitUtil.getId());
                    deliveryInfo.setUser(finalUser);
                    deliveryInfo.setDeliveryName(e.getDeliveryName());
                    deliveryInfo.setPhoneNumber(e.getPhoneNumber());
                    deliveryInfo.setDeliveryAddress(e.getDeliveryAddress());
                    deliveryInfo.setDeliveryRemark(e.getDeliveryRemark());
                    deliveryInfo.setDeliveryIndex(Integer.parseInt(e.getDeliveryIndex()));

                    deliveryInfoRepository.save(deliveryInfo);
                });
            }
            if(user != null){

                return new Result(FruitMsgEnum.AddUserSuccess);

            }else {
                throw new FruitException(FruitMsgEnum.AddUserFailed);
            }
        }catch (Exception e){

            throw new FruitException(FruitMsgEnum.Exception);
        }

    }

    @Override
    public Result getUserList(Pageable pageable) throws FruitException {
        try{
            Result result = new Result();

            Page<User> userPage = userRepository.findAll(pageable);

            if( userPage != null && userPage.getContent()!= null &&
                    !userPage.getContent().isEmpty()){

                result.setResponseReplyInfo(userPage);

                return result;
            }else{

                throw new FruitException(FruitMsgEnum.UserListEmpty);

            }

        }catch (Exception e){

            throw new FruitException(FruitMsgEnum.Exception);

        }
    }

    @Override
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken)
                && authentication.isAuthenticated();

    }

    @Override
    public void autoLogin(String email, String password) {
        UserDetails userDetails = this.findByUsername(email);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password,
                        userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.findByUsername(email);
        if (user == null) {
            throw new UsernameNotFoundException(
                    String.format("User with email %s was not found!", email));
        }
        return user;
    }
}