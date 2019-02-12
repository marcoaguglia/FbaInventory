package utility;

public enum Is_winner {
    TRUE {
        public String toString() {
            return "true";
        }
    },

    FALSE {
        public String toString() {
            return "false";
        }
    },
    UNKNOWN {
        public String toString() {
            return "unknown";
        }
    }
}


