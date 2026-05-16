package projeto.entidades;

public enum Cargo {

    ADM{
        @Override
        public boolean isAdmin() {
            return true;
        }
    },
    COMUM{
        @Override
        public boolean isAdmin() {
            return false;
        }
    };

    public abstract boolean isAdmin();
}
