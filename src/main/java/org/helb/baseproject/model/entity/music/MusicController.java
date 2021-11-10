package org.helb.baseproject.model.entity.music;

import org.helb.baseproject.api.statistic.Statistic;
import org.helb.baseproject.api.statistic.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MusicController {

    @Autowired
    private MusicRepository musicRepository;

    @PostMapping("/postMusic")
    @ResponseBody
    public String postStat(@RequestBody Music music){
        musicRepository.save(music);
        return "Saved";
    }

    @PostMapping("/addManualMusic")
    public String addManualMusic(@ModelAttribute Music music){

        return "Saved";
    }

    @GetMapping("/getMusic")
    @ResponseBody
    public List<Music> getMusic(){
        return (List<Music>) musicRepository.findAll();

    }
    @GetMapping("/getOneMusic/{id}")
    @ResponseBody
    public Music getOneMusic(@PathVariable("id") int id) {
        return musicRepository.findById(id).get();
    }
}
