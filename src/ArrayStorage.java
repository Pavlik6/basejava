import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static int count;

    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    void save(Resume r) {
        if (count < 10000) {
            storage[count] = r;
            count++;
        } else {
            System.out.println("Закончилось свободное место для новых резюме!");
        }
    }

    Resume get(String uuid) {
        Resume temp = new Resume();
        temp.uuid = uuid;
        return temp;
    }

    void delete(String uuid) {
        for (int i = 0; i < count; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int num = 0;

        for (int i = 0; i < count; i++) {
            if (storage[i] != null) {
                storage[num] = storage[i];
                num++;
            }
        }

        Resume[] temp = Arrays.copyOf(storage, num);

        return temp;
    }

    int size() {
        return count;
    }
}
