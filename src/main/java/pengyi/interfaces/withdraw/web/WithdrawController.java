package pengyi.interfaces.withdraw.web;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pengyi.application.user.driver.representation.DriverRepresentation;
import pengyi.application.withdraw.IWithdrawAppService;
import pengyi.application.withdraw.command.EditWithdrawCommand;
import pengyi.application.withdraw.command.ListWithdrawCommand;
import pengyi.application.withdraw.representation.WithdrawExtendRepresentation;
import pengyi.application.withdraw.representation.WithdrawRepresentation;
import pengyi.core.commons.Constants;
import pengyi.core.util.CoreDateUtils;
import pengyi.domain.model.user.BaseUser;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * Created by pengyi on 2016/5/6.
 */
@Controller
@RequestMapping("/withdraw")
public class WithdrawController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IWithdrawAppService withdrawAppService;

    @RequestMapping(value = "/finish")
    public ModelAndView finish(EditWithdrawCommand command, RedirectAttributes redirectAttributes, HttpSession session) {
        BaseUser user = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        command.setLoginUser(user.getId());
        AlertMessage alertMessage;
        try {
            withdrawAppService.finish(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/withdraw/list").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        alertMessage = new AlertMessage(AlertMessage.MessageType.SUCCESS, AlertMessage.MessageType.SUCCESS.getName());
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/withdraw/list").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
    }

    @RequestMapping(value = "/list")
    public ModelAndView list(ListWithdrawCommand command) {
        return new ModelAndView("/withdraw/list", "pagination", withdrawAppService.pagination(command))
                .addObject("command", command);
    }

    @RequestMapping("/export_excel")
    public void exportExcel(ListWithdrawCommand command, HttpServletResponse response) {
        List<WithdrawExtendRepresentation> representations = withdrawAppService.exportExcel(command);
        response.setContentType("application/vnd.ms-excel");
        String codedFileName;
        OutputStream fOut = null;

        try {
            codedFileName = java.net.URLEncoder.encode("提现" + CoreDateUtils.formatDateTime(new Date()), "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();

            HSSFRow rowTitle = sheet.createRow(0);//创建一行
            HSSFCell cellTitle = rowTitle.createCell(0);//创建一列
            cellTitle.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle.setCellValue("顺序号");
            HSSFCell cellTitle1 = rowTitle.createCell(1);
            cellTitle1.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle1.setCellValue("收款账号名称");
            HSSFCell cellTitle2 = rowTitle.createCell(2);
            cellTitle2.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle2.setCellValue("收款账号");
            HSSFCell cellTitle3 = rowTitle.createCell(3);
            cellTitle3.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle3.setCellValue("金额");
            HSSFCell cellTitle4 = rowTitle.createCell(4);
            cellTitle4.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle4.setCellValue("币种");
            HSSFCell cellTitle5 = rowTitle.createCell(5);
            cellTitle5.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle5.setCellValue("汇款用途");
            HSSFCell cellTitle6 = rowTitle.createCell(6);
            cellTitle6.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle6.setCellValue("收款账号开户行");
            HSSFCell cellTitle7 = rowTitle.createCell(7);
            cellTitle7.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle7.setCellValue("收款账号省份");
            HSSFCell cellTitle8 = rowTitle.createCell(8);
            cellTitle8.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle8.setCellValue("收款账号地市");
            HSSFCell cellTitle9 = rowTitle.createCell(9);
            cellTitle9.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle9.setCellValue("收款账号地区码");
            HSSFCell cellTitle10 = rowTitle.createCell(10);
            cellTitle10.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle10.setCellValue("报销号");
            HSSFCell cellTitle11 = rowTitle.createCell(11);
            cellTitle11.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle11.setCellValue("单据张数");
            HSSFCell cellTitle12 = rowTitle.createCell(12);
            cellTitle12.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle12.setCellValue("备注信息");
            HSSFCell cellTitle13 = rowTitle.createCell(13);
            cellTitle13.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle13.setCellValue("收款账户短信通知手机号码");
            HSSFCell cellTitle14 = rowTitle.createCell(14);
            cellTitle14.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle14.setCellValue("付款账号开户行");
            HSSFCell cellTitle15 = rowTitle.createCell(15);
            cellTitle15.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle15.setCellValue("付款账号");
            HSSFCell cellTitle16 = rowTitle.createCell(16);
            cellTitle16.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle16.setCellValue("付款账号名称");
            HSSFCell cellTitle17 = rowTitle.createCell(17);
            cellTitle17.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle17.setCellValue("付方账号是否他行账号");

            for (int i = 1; i <= representations.size(); i++) {
                WithdrawExtendRepresentation representation = representations.get(i - 1);
                String name = ((DriverRepresentation) representation.getUser()).getName();//收款账户名称
                String bankNo = ((DriverRepresentation) representation.getUser()).getBankCardNo();//收款账户账号
                String money = representation.getMoney() == null ? null : representation.getMoney().toString();//金额
                String moneyType = "人民币";//币种
                String purpose = "工资汇款";//用途
                String bankType = "工行";//开户银行
                String province = "重庆";//省份
                String city = "重庆";//地级市
                String areaCode = "";//地区码
                String reimbursement = "";//报销号
                String billNumber = "";//单据张数
                String remarks = "备注信息";//备注
                String phone = representation.getUser().getUserName();//短信通知电话号码
                String payBankType = "中国工商银行璧山支行";//付款开户银行
                String payBankNo = "123132";//付款账号
                String payName = "公司名称";//付款账号名称
                String payIsOtherBank = "否";//付方账号是否他行账号
                HSSFRow row = sheet.createRow(i);//创建一行
                HSSFCell cell = row.createCell(0);//创建一列
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(i);
                HSSFCell cell1 = row.createCell(1);
                cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell1.setCellValue(name);
                HSSFCell cell2 = row.createCell(2);
                cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell2.setCellValue(bankNo);
                HSSFCell cell3 = row.createCell(3);
                cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell3.setCellValue(money);
                HSSFCell cell4 = row.createCell(4);
                cell4.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell4.setCellValue(moneyType);
                HSSFCell cell5 = row.createCell(5);
                cell5.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell5.setCellValue(purpose);
                HSSFCell cell6 = row.createCell(6);
                cell6.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell6.setCellValue(bankType);
                HSSFCell cell7 = row.createCell(7);
                cell7.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell7.setCellValue(province);
                HSSFCell cell8 = row.createCell(8);
                cell8.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell8.setCellValue(city);
                HSSFCell cell9 = row.createCell(9);
                cell9.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell9.setCellValue(areaCode);
                HSSFCell cell10 = row.createCell(10);
                cell10.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell10.setCellValue(reimbursement);
                HSSFCell cell11 = row.createCell(11);
                cell11.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell11.setCellValue(billNumber);
                HSSFCell cell12 = row.createCell(12);
                cell12.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell12.setCellValue(remarks);
                HSSFCell cell13 = row.createCell(13);
                cell13.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell13.setCellValue(phone);
                HSSFCell cell14 = row.createCell(14);
                cell14.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell14.setCellValue(payBankType);
                HSSFCell cell15 = row.createCell(15);
                cell15.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell15.setCellValue(payBankNo);
                HSSFCell cell16 = row.createCell(16);
                cell16.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell16.setCellValue(payName);
                HSSFCell cell17 = row.createCell(17);
                cell17.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell17.setCellValue(payIsOtherBank);
            }
            fOut = response.getOutputStream();
            workbook.write(fOut);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fOut.flush();
                fOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
