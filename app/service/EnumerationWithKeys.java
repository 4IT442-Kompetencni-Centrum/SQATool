package service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * EnumerationWithKeys contains all keys for classes which extends AbstractEnumWithoutKey
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
public class EnumerationWithKeys {
	public static final String ADMIN_KEY = "admin";
	public static final String MANAGER_KC_KEY = "manazerKC";
	public static final String HEAD_KC_KEY = "vedouciKC";
	public static final String MEMBER_KEY = "clen";

	public static final String ACTIVITY_TYPE_DEFAULT = "aktivita";
	public static final String ACTIVITY_STATE_EMPTY = "volna";
	public static final String ACTIVITY_STATE_FULL = "plna";

	public static final String TYPE_ROLE_ON_ACTIVITY_ORGANIZER = "zakladatel";
	public static final String TYPE_ROLE_ON_ACTIVITY_LOGGED = "prihlaseny";

    public static final String STATE_USER_ACTIVE = "aktivni";
    public static final String STATE_USER_APPLICANT = "zadatel";
    public static final String STATE_USER_INACTIVE = "neaktivni";
    
    public static final String PROJECT_MANAGER_KEY = "vedouci";
    public static final String PROJECT_MEMBER_KEY = "clen";
    
    public static final String STATE_HOURS_WORKED_CREATED = "zadana";
    public static final String STATE_HOURS_WORKED_APPROVED = "schvalena";
    public static final String STATE_HOURS_WORKED_REJECTED = "zamitnuta";

}
