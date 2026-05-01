package projeto.entidades;

public enum Cargo {

    ADM{
        @Override
        public boolean podeGerir() {
            return true;
        }
    },
    COMUM{
        @Override
        public boolean podeGerir() {
            return false;
        }
    };

    public abstract boolean podeGerir();
}
