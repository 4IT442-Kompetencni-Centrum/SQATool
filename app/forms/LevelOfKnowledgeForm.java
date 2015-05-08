package forms;


import daos.impl.DAOs;
import models.LevelOfKnowledge;
import models.TypeKnowledge;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LevelOfKnowledgeForm {

    private List<Map<String, String>> knowledgeLevels;


    public List<Map<String, String>> getKnowledgeLevels() {
        return knowledgeLevels;
    }

    public LevelOfKnowledgeForm setKnowledgeLevels(List<Map<String, String>> knowledgeLevels) {
        this.knowledgeLevels = knowledgeLevels;
        return this;
    }

    public LevelOfKnowledgeForm() {
    }

    public LevelOfKnowledgeForm(List<LevelOfKnowledge> list) {
        for (LevelOfKnowledge levelOfKnowledge : list) {
            HashMap<String, String> item = new HashMap<>();
            item.put("id", levelOfKnowledge.getLevelOfKnowledgeId().toString());
            item.put("value", levelOfKnowledge.getValue());
            knowledgeLevels.add(item);
        }
    }


    public List<LevelOfKnowledge> getList() {
        List<LevelOfKnowledge> list = new LinkedList<>();

        if (this.getKnowledgeLevels() == null) {
            return list;
        }

        for (Map<String, String> map : this.getKnowledgeLevels()) {
            LevelOfKnowledge levelOfKnowledge = null;

            if (map.get("value") == null || map.get("value").equals("")) {
                continue;
            }

            if (map.get("id") != null && !map.get("id").equals("")) {
                levelOfKnowledge = DAOs.getLevelOfKnowledgeDao().findById(Long.parseLong(map.get("id")));
            }

            if (levelOfKnowledge == null) {
                levelOfKnowledge = new LevelOfKnowledge();
            }

            levelOfKnowledge.setValue(map.get("value"));

            list.add(levelOfKnowledge);
        }

        return list;
    }
}
