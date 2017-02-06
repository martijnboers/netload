package netload.view;
/**
 * Copyright (C) 01/02/17 martijn
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
import netload.controller.Scrape;
import netload.controller.StatsController;
import netload.controller.Update;
import netload.model.Day;
import netload.model.Total;
import netload.model.Week;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
public class View {
    private Scrape scraper;
    private Update update;
    private StatsController controller;
    static Logger log;

    public View() {
        scraper = new Scrape();
        update = new Update();
        controller = new StatsController();
        log = Logger.getLogger(View.class.getName());
    }

    @RequestMapping("/scrape/days")
    @CrossOrigin(origins = "*")
    public ArrayList<Day> getAllDays() {
        try {
            return scraper.getAllDays();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/scrape/week/list")
    public ArrayList<Day> getWeekList() {
        try {
            return scraper.getWeekList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/scrape/week")
    public Week getWeek() {
        try {
            return scraper.getWeek();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/update")
    public boolean update() {
        try {
            return update.update();
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/stats/days")
    public List<Day> getAllDaysStat() {
        try {
            return controller.getAllDays();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/stats/total")
    public Total getAllDaysStatTotal() {
        try {
            return controller.getTotalStats();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/stats/weeks")
    public ArrayList<Week> getWeeksTotal() {
        try {
            return controller.getTotalWeeks();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

}
