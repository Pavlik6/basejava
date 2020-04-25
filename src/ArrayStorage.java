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
        temp.uuid = "Нет такого резюме!";

        for (int i = 0; i < count; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }

        return temp;
    }

    void delete(String uuid) {
        int i;

        for (i = 0; i < count; i++) {
            if (storage[i].uuid.equals(uuid)) {
                break;
            }
        }

        for (int j = i; j < count - 1; j++) {
            storage[j] = storage[j + 1];
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
