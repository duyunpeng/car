package pengyi.interfaces.moneydetailed.web;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.RichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pengyi.application.moneydetailed.IMoneyDetailedAppService;
import pengyi.application.moneydetailed.command.ListMoneyDetailedCommand;
import pengyi.application.moneydetailed.representation.MoneyDetailedRepresentation;
import pengyi.application.order.representation.OrderRepresentation;
import pengyi.core.util.CoreDateUtils;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by YJH on 2016/3/14.
 */
@Controller
@RequestMapping("/money_detailed")
public class MoneyDetailedController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IMoneyDetailedAppService moneyDetailedAppService;

    @RequestMapping(value = "/list")
    public ModelAndView list(ListMoneyDetailedCommand command) {
        return new ModelAndView("/moneydetailed/list", "pagination", moneyDetailedAppService.pagination(command))
                .addObject("command", command);
    }

    @RequestMapping(value = "/show/{id}")
    public ModelAndView show(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        MoneyDetailedRepresentation moneyDetailed = null;
        try {
            moneyDetailed = moneyDetailedAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/money_detailed/list");
        }

        return new ModelAndView("/moneydetailed/show", "moneyDetailed", moneyDetailed);
    }

    @RequestMapping("export_excel")
    public void exportExcel(ListMoneyDetailedCommand command, HttpServletResponse response) {
        List<MoneyDetailedRepresentation> representations = moneyDetailedAppService.exportExcel(command);
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            // 进行转码，使其支持中文文件名
            codedFileName = java.net.URLEncoder.encode("资金流向" + CoreDateUtils.formatDateTime(new Date()), "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
            // response.addHeader("Content-Disposition", "attachment;   filename=" + codedFileName + ".xls");
            // 产生工作簿对象
            HSSFWorkbook workbook = new HSSFWorkbook();
            //产生工作表对象
            HSSFSheet sheet = workbook.createSheet();

            HSSFRow rowTitle = sheet.createRow(0);//创建一行
            HSSFCell cellTitle = rowTitle.createCell(0);//创建一列
            cellTitle.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle.setCellValue("用户名");
            HSSFCell cellTitle1 = rowTitle.createCell(1);
            cellTitle1.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle1.setCellValue("资金流向");
            HSSFCell cellTitle2 = rowTitle.createCell(2);
            cellTitle2.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle2.setCellValue("金额");
            HSSFCell cellTitle3 = rowTitle.createCell(3);
            cellTitle3.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle3.setCellValue("创建时间");
            for (int i = 1; i <= representations.size(); i++) {
                MoneyDetailedRepresentation representation = representations.get(i - 1);
                HSSFRow row = sheet.createRow(i);//创建一行
                HSSFCell cell = row.createCell(0);//创建一列
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(representation.getBaseUser().getUserName());
                HSSFCell cell1 = row.createCell(1);//
                cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell1.setCellValue(representation.getFlowType().getName());
                HSSFCell cell2 = row.createCell(2);//
                cell2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                cell2.setCellValue(representation.getMoney() == null ? "" : representation.getMoney().toString());
                HSSFCell cell3 = row.createCell(3);
                cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell3.setCellValue(CoreDateUtils.formatDate(representation.getCreateDate(), CoreDateUtils.DATETIME));
            }
            fOut = response.getOutputStream();
            workbook.write(fOut);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
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
