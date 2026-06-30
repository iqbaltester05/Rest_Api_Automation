package utilClass;

public final class RetryUtil {

    private RetryUtil() {}

    public static void retry(Runnable operation, int maxRetries) {

        int attempt = 0;

        while (true) {
            try {
                operation.run();
                return;
            } catch (Exception e) {
                if (attempt >= maxRetries) {
                    throw e;
                }
                attempt++;
            }
        }
    }
}