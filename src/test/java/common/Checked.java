package common;


public interface Checked<T> {
    /**
     * Проверка, что открылась нужная страница
     * @return страница
     */
    T checkOpen();
}
