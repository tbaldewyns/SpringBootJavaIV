package org.helb.baseproject.api.statistic;

import org.helb.baseproject.model.entity.history.History;
import org.helb.baseproject.model.entity.history.HistoryRepository;
import org.helb.baseproject.model.entity.music.Music;
import org.helb.baseproject.model.entity.music.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.*;

@Controller
public class StatisticController {

    @Autowired
    private StatisticRepository statisticRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @GetMapping("/updateStat")
    @ResponseBody
    public HashMap<Music, Integer> updateStat(){

        HashMap<Music, Integer> historyDico = new HashMap<>();
        int maxValueInMap = 0;

        ArrayList<History> musicList;

        musicList = (ArrayList<History>) historyRepository.findAll();

        for (History history : musicList) {
            historyDico.merge(history.getMusic(), 1, (a, b) -> a + b);
        }

        maxValueInMap=(Collections.max(historyDico.values()));

        System.out.println(maxValueInMap);
        return historyDico;
    }

    @PostMapping("/postStat")
    @ResponseBody
    public String postStat(@RequestBody Statistic statistic){

        statisticRepository.save(statistic);
        return "Saved";
    }

    @GetMapping("/getStat")
    @ResponseBody
    public List<Statistic> getStat(){
        return (List<Statistic>) statisticRepository.findAll();

    }
    @GetMapping("/getOneStat/{id}")
    @ResponseBody
    public Statistic getOneStat(@PathVariable("id") int id) {
        return statisticRepository.findById(id).get();
    }
}
