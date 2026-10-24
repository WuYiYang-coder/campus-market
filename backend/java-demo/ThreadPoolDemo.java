import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
public class ThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger seq = new AtomicInteger(1);
        AtomicInteger rejectCount = new AtomicInteger(0);
        Map<Runnable,Integer> taskMap = new java.util.concurrent.ConcurrentHashMap<>();
        ThreadFactory factory = r -> new Thread(r, "worker-" + seq.getAndIncrement());
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                2,
                4,
                3,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                factory,
                (r, ex) -> {
                    rejectCount.incrementAndGet();
                    Integer id = taskMap.get(r);
                    System.out.println("rejected: task-" + id);
                }
        );
        for (int i = 1; i <= 8; i++) {
            int taskId = i;
            Runnable task = () -> {
                System.out.println(Thread.currentThread().getName()
                        + "start task " + taskId);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(Thread.currentThread().getName()
                        + "end task " + taskId);
            };
            taskMap.put(task,taskId);
            pool.execute(task);
        }
        pool.shutdown();
        pool.awaitTermination(20, TimeUnit.SECONDS);
        System.out.println("总拒绝次数" + rejectCount.get());
        System.out.println("all tasks finished");
    }
    }