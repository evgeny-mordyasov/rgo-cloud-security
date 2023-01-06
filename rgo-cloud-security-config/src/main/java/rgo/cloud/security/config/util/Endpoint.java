package rgo.cloud.security.config.util;

public class Endpoint {
    private final static String PREFIX_URL = "/api/v1";

    public final static String ENTITY_ID_VARIABLE = "/{entityId}";

    public static final class Authorization {
        public final static String BASE_URL = PREFIX_URL + "/authorizations";

        public static final String SIGN_IN = "/sign-in";
        public static final String LOGOUT = "/logout";
        public static final String SIGN_UP = "/sign-up";
        public static final String CONFIRM_ACCOUNT = "/confirm-account";
        public static final String RESEND_TOKEN = "/resend-token";
    }

    public static final class Classification {
        public final static String BASE_URL = PREFIX_URL + "/classifications";
    }

    public static final class Me {
        public final static String BASE_URL = PREFIX_URL + "/me";

        public final static String CLIENT_ID_VARIABLE = "/{clientId}";
    }

    public static final class Client {
        public final static String BASE_URL = PREFIX_URL + "/clients";
    }

    public static final class File {
        public final static String BASE_URL = PREFIX_URL + "/files";

        public final static String DOCUMENT_ID_VARIABLE = "/{documentId}";

        public final static String RESOURCE = "/resource";
        public final static String FREE_LANGUAGES = "/free-languages/{documentId}";
    }

    public static final class Language {
        public final static String BASE_URL = PREFIX_URL + "/languages";
    }
}
