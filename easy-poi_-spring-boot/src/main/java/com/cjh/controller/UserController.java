package com.cjh.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.cjh.entity.User;
import com.cjh.service.UserService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author: huahua
 * @Date: 2020-09-15 10:27
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 导出excel
     *
     * @param response
     * @throws IOException
     */
    @RequestMapping("export")
    public void ExportExcel(HttpServletResponse response) throws IOException {
        List<User> users = userService.findAll();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户列表信息", "用户信息"), User.class, users);
        response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode("用户列表.xls", "UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

    /**
     * excel导入
     *
     * @param excelFile
     * @return
     * @throws Exception
     */
    @RequestMapping("/import")
    public String importExcel(MultipartFile excelFile) throws Exception {
//        log.info("文件名："+excelFile.getOriginalFilename());
        //excel导入
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);

        List<User> users = ExcelImportUtil.importExcel(excelFile.getInputStream(), User.class, params);
//        for (User user : users) {
//            System.out.println(user);
//        }
        userService.saveAll(users);
        return "redirect:/user/findAll";
    }

    /**
     * 查询所有
     *
     * @param model
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "index";
    }

}
