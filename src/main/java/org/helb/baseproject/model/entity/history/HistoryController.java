package org.helb.baseproject.model.entity.history;

import org.helb.baseproject.model.entity.music.Music;
import org.helb.baseproject.model.entity.music.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HistoryController {

    @Autowired
    private HistoryRepository historyRepository;

    @PostMapping("/postHistory")
    @ResponseBody
    public String postStat(@RequestBody History history){
        historyRepository.save(history);
        return "Saved";
    }

    @GetMapping("/getHistory")
    @ResponseBody
    public List<History> getMusic(){
        return (List<History>) historyRepository.findAll();

    }
    @GetMapping("/getOneHistory/{id}")
    @ResponseBody
    public History getOneHistory(@PathVariable("id") int id) {
        return historyRepository.findById(id).get();
    }
}
