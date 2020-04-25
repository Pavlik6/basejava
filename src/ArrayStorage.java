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
        int i;

        for (i = 0; i < count; i++) {
            if (storage[i].uuid.equals(uuid)) {
                break;
            }
        }

        return storage[i];
    }

    void delete(String uuid) {
        int num = 0;

        for (int i = 0; i < count; i++) {
            if (storage[i].uuid.equals(uuid)) {
                continue;
            } else {
                storage[num] = storage[i];
                num++;
            }
        }

        count--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, count);
    }

    int size() {
        return count;
    }
}
