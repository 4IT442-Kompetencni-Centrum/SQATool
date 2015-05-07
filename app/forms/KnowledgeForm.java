package forms;


import daos.impl.DAOs;
import models.Knowledge;
import models.LevelOfKnowledge;
import models.TypeKnowledge;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class KnowledgeForm {
    List<Map<String, String>> knowledge;

    public List<Map<String, String>> getKnowledge() {
        return knowledge;
    }

    public KnowledgeForm setKnowledge(List<Map<String, String>> knowledge) {
        this.knowledge = knowledge;
        return this;
    }


    public List<Knowledge> getKnowledges() {
        List<Knowledge> knowledges = new LinkedList<>();

        if (this.knowledge == null) {
            return knowledges;
        }
        for (Map<String, String> map : this.knowledge) {

            if (map.get("id") != null && !map.get("id").equals("")) {
                Knowledge knowledge = DAOs.getKnowledgeDao().findById(Long.parseLong(map.get("id")));
                knowledges.add(knowledge);
                continue;
            }

            Knowledge knowledge = new Knowledge();
            LevelOfKnowledge levelOfKnowledge = DAOs.getLevelOfKnowledgeDao().findByKey(map.get("level"));
            TypeKnowledge typeKnowledge = DAOs.getTypeKnowledgeDao().findByKey(map.get("name"));
            knowledge.setLevelOfKnowledge(levelOfKnowledge);
            knowledge.setTypeKnowledge(typeKnowledge);
            knowledges.add(knowledge);
        }

        return knowledges;
    }
}
