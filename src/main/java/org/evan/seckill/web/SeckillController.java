package org.evan.seckill.web;

import org.evan.seckill.dto.Exposer;
import org.evan.seckill.dto.SeckillExcution;
import org.evan.seckill.dto.SeckillResult;
import org.evan.seckill.entity.Seckill;
import org.evan.seckill.enums.SeckillStateEnum;
import org.evan.seckill.exception.RepeatSeckillException;
import org.evan.seckill.exception.SeckillCloseException;
import org.evan.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Evan on 9/22/2016.
 */
@Controller
@RequestMapping("/seckill") // url:/ģ��/��Դ/{id}
public class SeckillController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(name = "/list2", method = RequestMethod.GET)
    public String list2(Model model) {

        List<Seckill> seckills = seckillService.getSeckillList();
        model.addAttribute("list", seckills);
        return "list"; // WEB-INF/jsp/list.jsp
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {

        if (null == seckillId) {
            return "redirect:/seckill/list";
        }

        Seckill seckill = seckillService.getSeckillById(seckillId);
        if (null == seckill) {
            return "forword:/seckill/list";
        }
        model.addAttribute("seckill",seckill);
        return "detail";
    }

    @RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposerUrl(@PathVariable("seckillId") long seckillId) {

        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true, exposer);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = new SeckillResult<Exposer>(false, ex.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST,
            produces = {"applicaiotn/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExcution> excutionSeckill(@PathVariable("seckillId") Long seckillId,
                                                          @PathVariable("md5") String md5,
                                                          @CookieValue(value = "killPhone", required = false) Long phone) {

        if (null == phone) {
            return new SeckillResult<SeckillExcution>(false, "δע���û�");
        }
        try {
            SeckillExcution seckillExcution = seckillService.executeSeckill(seckillId, phone, md5);
            return new SeckillResult<SeckillExcution>(true, seckillExcution);
        } catch (SeckillCloseException e) {
            SeckillExcution seckillExcution = new SeckillExcution(seckillId, SeckillStateEnum.END);
            return new SeckillResult<SeckillExcution>(false, seckillExcution);
        } catch (RepeatSeckillException e) {
            SeckillExcution seckillExcution = new SeckillExcution(seckillId, SeckillStateEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExcution>(false, seckillExcution);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            SeckillExcution seckillExcution = new SeckillExcution(seckillId, SeckillStateEnum.INNER_ERROR);
            return new SeckillResult<SeckillExcution>(false, seckillExcution);
        }
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    public SeckillResult<Long> time() {
        Date now = new Date();
        return new SeckillResult<Long>(true, now.getTime());
    }
}
