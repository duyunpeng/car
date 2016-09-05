package pengyi.interfaces.order.web;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pengyi.application.order.IOrderAppService;
import pengyi.application.order.IOrderWayPointAppService;
import pengyi.application.order.command.ListOrderCommand;
import pengyi.application.order.representation.OrderRepresentation;
import pengyi.application.order.representation.OrderWayPointRepresentation;
import pengyi.core.util.CoreDateUtils;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by YJH on 2016/3/14.
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IOrderAppService orderAppService;

    @Autowired
    private IOrderWayPointAppService orderWayPointAppService;

    @RequestMapping(value = "/list")
    public ModelAndView list(ListOrderCommand command) {
        return new ModelAndView("/order/list", "pagination", orderAppService.pagination(command))
                .addObject("command", command);
    }

    @RequestMapping(value = "/show/{id}")
    public ModelAndView show(@PathVariable("id") String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        OrderRepresentation order = null;

        try {
            order = orderAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/order/list").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/order/show", "order", order);
    }

    @RequestMapping(value = "/way/{id}")
    public ModelAndView way(@PathVariable("id") String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        List<OrderWayPointRepresentation> wayPoints = null;

        try {
            wayPoints = orderWayPointAppService.list(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/order/list");
        }

        if (null == wayPoints || wayPoints.size() < 2) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("order.way.point.size.message", null, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/order/list").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/order/way", "wayPoints", wayPoints);
    }

    @RequestMapping("/export_excel")
    public void exportExcel(ListOrderCommand command, HttpServletResponse response) {

        List<OrderRepresentation> representations = orderAppService.exportExcel(command);

        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            // 进行转码，使其支持中文文件名
            codedFileName = java.net.URLEncoder.encode("订单"+ CoreDateUtils.formatDateTime(new Date()), "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
            // response.addHeader("Content-Disposition", "attachment;   filename=" + codedFileName + ".xls");
            // 产生工作簿对象
            HSSFWorkbook workbook = new HSSFWorkbook();
            //产生工作表对象
            HSSFSheet sheet = workbook.createSheet();

            HSSFRow rowTitle = sheet.createRow(0);//创建一行
            HSSFCell cellTitle = rowTitle.createCell(0);//创建一列
            cellTitle.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle.setCellValue("订单号");
            HSSFCell cellTitle1 = rowTitle.createCell(1);
            cellTitle1.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle1.setCellValue("下单人");
            HSSFCell cellTitle2 = rowTitle.createCell(2);
            cellTitle2.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle2.setCellValue("接单人");
            HSSFCell cellTitle3 = rowTitle.createCell(3);
            cellTitle3.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle3.setCellValue("订单金额");
            HSSFCell cellTitle4 = rowTitle.createCell(4);
            cellTitle4.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle4.setCellValue("下单时间");
            HSSFCell cellTitle5 = rowTitle.createCell(5);
            cellTitle5.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle5.setCellValue("司机类型");
            HSSFCell cellTitle6 = rowTitle.createCell(6);
            cellTitle6.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle6.setCellValue("车辆类型");
            HSSFCell cellTitle7 = rowTitle.createCell(7);
            cellTitle7.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle7.setCellValue("订单状态");
            HSSFCell cellTitle8 = rowTitle.createCell(8);
            cellTitle8.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle8.setCellValue("订单总额");

            for (int i = 1; i <= representations.size(); i++) {
                OrderRepresentation representation = representations.get(i - 1);
                HSSFRow row = sheet.createRow(i);//创建一行
                HSSFCell cell = row.createCell(0);//创建一列
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(representation.getOrderNumber());
                HSSFCell cell1 = row.createCell(1);
                cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell1.setCellValue(representation.getOrderUser().getUserName());
                HSSFCell cell2 = row.createCell(2);
                cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell2.setCellValue(representation.getReceiveUser() == null ? "" : representation.getReceiveUser().getUserName());
                HSSFCell cell3 = row.createCell(3);
                cell3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                cell3.setCellValue(representation.getShouldMoney() == null ? "" : representation.getShouldMoney().toString());
                HSSFCell cell4 = row.createCell(4);
                cell4.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell4.setCellValue(representation.getCreateDate());
                HSSFCell cell5 = row.createCell(5);
                cell5.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell5.setCellValue(representation.getDriverType() == null ? "" : representation.getDriverType().getName());
                HSSFCell cell6 = row.createCell(6);
                cell6.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell6.setCellValue(representation.getCarType() == null ? "" : representation.getCarType().getName());
                HSSFCell cell7 = row.createCell(7);
                cell7.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell7.setCellValue(representation.getOrderStatus().getName());
                HSSFCell cell8 = row.createCell(8);
                cell8.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                cell8.setCellValue(representation.getShouldMoney() == null ? "" : representation.getShouldMoney().add(representation.getExtraMoney()).toString());
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
