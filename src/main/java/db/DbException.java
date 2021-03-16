package db;

public class DbException  extends RuntimeException {
    private static final long serialVersionUID = 1l;

    public DbException (String message) {
        super(message);
    }
}
