package daos.impl;

import daos.AcademicWorkDao;
import daos.ActivityDao;
import models.AcademicWork;
import models.Activity;
import play.db.jpa.JPA;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Implementation of DAO for DB operations for Activity objects.
 *
 * @author Aleš Jiránek <a.jiranek@gmail.com>
 */
public class AcademicWorkDaoImpl extends AbstractVersionedDaoImpl<AcademicWork>
        implements AcademicWorkDao {
}
