package org.helb.baseproject.api.statistic;

import org.helb.baseproject.model.entity.history.History;
import org.helb.baseproject.model.entity.history.HistoryRepository;
import org.helb.baseproject.model.entity.music.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class StatisticController {

    @Autowired
    private StatisticRepository statisticRepository;

    @Autowired
    private HistoryRepository historyRepository;

    //Ajout automatique d'une statistique au clic
    @PostMapping("/updateStat")
    @ResponseBody
    public Statistic updateStat(){

        Statistic statistic = new Statistic();
        int totalTimeListened = 0;
        Music maxValueKeyInMusicMap = null;
        String maxValueKeyInGenderMap = "";
        int year = Calendar.getInstance().get(Calendar.YEAR);

        HashMap<Music, Integer> historyDico = new HashMap<>();
        HashMap<String, Integer> genderDico = new HashMap<>();

        ArrayList<History> musicList;

        //Récupère tout l'historique
        musicList = (ArrayList<History>) historyRepository.findAll();

        for (History history : musicList) {
            //Efectue la recherche sur l'historique de cette année
            if ((history.getListenedAt().getYear() + 1900) == year){
                //Mets les dictionnaires à jour
                genderDico.merge(history.getMusic().getGender(), 1, (a, b) -> a + b);
                historyDico.merge(history.getMusic(), 1, (a, b) -> a + b);
                //Calcul le temps d'écoute
                totalTimeListened += history.getMusic().getTime();
            }
        }
        //Recherche le nombre d'occurrence maximum dans les HashMaps
        maxValueKeyInMusicMap = historyDico.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
        maxValueKeyInGenderMap = genderDico.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();

        statistic.setMostListenedMusic(maxValueKeyInMusicMap.getName().replace("\n", "").replace("\r", "")+" - "+maxValueKeyInMusicMap.getAuthor());
        statistic.setYear(year);
        statistic.setTotalMinuteListened(totalTimeListened);
        statistic.setGenderMostListened(maxValueKeyInGenderMap);
        statisticRepository.save(statistic);

        return statistic;
    }

    //Ajout d'une stat manuellement
    @PostMapping("/postStat")
    @ResponseBody
    public String postStat(@RequestBody Statistic statistic){

        statisticRepository.save(statistic);
        return "Saved";
    }
    //Récupère la liste de toutes les statistics
    @GetMapping("/getStat")
    @ResponseBody
    public List<Statistic> getStat(){
        return (List<Statistic>) statisticRepository.findAll();

    }
    //Récupère une statistique en fonction de l'Id
    @GetMapping("/getOneStat/{id}")
    @ResponseBody
    public Statistic getOneStat(@PathVariable("id") int id) {
        return statisticRepository.findById(id).get();
    }
}
