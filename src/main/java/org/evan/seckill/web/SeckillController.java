package org.evan.seckill.web;

import org.evan.seckill.dto.Exposer;
import org.evan.seckill.dto.SeckillResult;
import org.evan.seckill.entity.Seckill;
import org.evan.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Evan on 9/22/2016.
 */
@Controller
@RequestMapping("/seckill") // url:/Ä£¿é/×ÊÔ´/{id}
public class SeckillController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(name = "/list", method = RequestMethod.GET)
    public String list(Model model) {

        List<Seckill> seckills = seckillService.getSeckillList();
        model.addAttribute("list", seckills);
        return "list"; // WEB-INF/jsp/list.jsp
    }

    @RequestMapping(value = "/seckillId/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") long seckillId, Model mode) {

        if (null == seckillId) {
            return "redirect:/seckill/list";
        }

        Seckill seckill = seckillService.getSeckillById(seckillId);
        if (null == seckill) {
            return "forword:/seckill/list";
        }
        return "detail";
    }

    @RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST,
            produces = "{application/json;charset=UTF-8}")
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
}
