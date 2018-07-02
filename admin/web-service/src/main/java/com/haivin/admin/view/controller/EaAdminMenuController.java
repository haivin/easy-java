package com.haivin.admin.view.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.haivin.database.controller.BaseController;
import com.haivin.admin.view.entity.EaAdminMenu;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhouran
 * @since 2018-06-14
 */
@RestController
@Slf4j
@RequestMapping("/admin/menu")
public class EaAdminMenuController extends BaseController<EaAdminMenu> {

}