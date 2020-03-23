package com.ruoyi.bus.controller;

import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.text.Convert;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.bus.domain.BusiOrder;
import com.ruoyi.bus.service.IBusiOrderService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 接送订单管理Controller
 *
 * @author ruoyi
 * @date 2020-03-23
 */
@Controller
@RequestMapping("/bus/order")
public class BusiOrderController extends BaseController
{
    private String prefix = "bus/order";

    @Autowired
    private IBusiOrderService busiOrderService;

    @RequiresPermissions("bus:order:view")
    @GetMapping()
    public String order()
    {
        return prefix + "/order";
    }

            /**
         * 查询接送订单管理列表
         */
        @RequiresPermissions("bus:order:list")
        @PostMapping("/list")
        @ResponseBody
        public TableDataInfo list(BusiOrder busiOrder)
        {
            startPage();
            return getDataTable(busiOrderService.list(new QueryWrapper<>()));
        }
    
    /**
     * 导出接送订单管理列表
     */
    @RequiresPermissions("bus:order:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusiOrder busiOrder)
    {
        List<BusiOrder> list = busiOrderService.list(new QueryWrapper<>());
        ExcelUtil<BusiOrder> util = new ExcelUtil<BusiOrder>(BusiOrder.class);
        return util.exportExcel(list, "order");
    }

            /**
         * 新增接送订单管理
         */
        @GetMapping("/add")
        public String add()
        {
            return prefix + "/add";
        }
    
    /**
     * 新增保存接送订单管理
     */
    @RequiresPermissions("bus:order:add")
    @Log(title = "接送订单管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BusiOrder busiOrder)
    {
        return toAjax(busiOrderService.save(busiOrder));
    }

    /**
     * 修改接送订单管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BusiOrder busiOrder = busiOrderService.getById(id);
        mmap.put("busiOrder", busiOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存接送订单管理
     */
    @RequiresPermissions("bus:order:edit")
    @Log(title = "接送订单管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BusiOrder busiOrder)
    {
        return toAjax(busiOrderService.updateById(busiOrder));
    }

            /**
         * 删除接送订单管理
         */
        @RequiresPermissions("bus:order:remove")
        @Log(title = "接送订单管理", businessType = BusinessType.DELETE)
        @PostMapping( "/remove")
        @ResponseBody
        public AjaxResult remove(String ids)
        {
            return toAjax(busiOrderService.removeByIds(Arrays.asList(Convert.toStrArray(ids))));
        }
        }
