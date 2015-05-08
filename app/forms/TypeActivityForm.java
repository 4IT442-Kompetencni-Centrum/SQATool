package forms;


import daos.impl.DAOs;
import models.TypeActivity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TypeActivityForm {

    private List<Map<String, String>> activityTypes;


    public List<Map<String, String>> getActivityTypes() {
        return activityTypes;
    }

    public TypeActivityForm setActivityTypes(List<Map<String, String>> activityTypes) {
        this.activityTypes = activityTypes;
        return this;
    }

    public TypeActivityForm() {
    }

    public TypeActivityForm(List<TypeActivity> list) {
        for (TypeActivity typeActivity : list) {
            HashMap<String, String> item = new HashMap<>();
            item.put("id", typeActivity.getTypeActivityId().toString());
            item.put("value", typeActivity.getValue());
            activityTypes.add(item);
        }
    }


    public List<TypeActivity> getList() {
        List<TypeActivity> list = new LinkedList<>();

        if (this.getActivityTypes() == null) {
            return list;
        }

        for (Map<String, String> map : this.getActivityTypes()) {
            TypeActivity typeActivity = null;

            if (map.get("value") == null || map.get("value").equals("")) {
                continue;
            }

            if (map.get("id") != null && !map.get("id").equals("")) {
                typeActivity = DAOs.getTypeActivityDao().findById(Long.parseLong(map.get("id")));
            }

            if (typeActivity == null) {
                typeActivity = new TypeActivity();
            }

            typeActivity.setValue(map.get("value"));

            list.add(typeActivity);
        }

        return list;
    }
}
