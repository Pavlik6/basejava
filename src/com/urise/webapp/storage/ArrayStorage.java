package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private static int count;
    private boolean isAnswer = false;

    public void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    public void update(Resume r) {
        for (int i = 0; i < count; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                System.out.println("\nUpdate: Resume - " + r.getUuid() + " обновлено!");
                isAnswer = true;
                break;
            }
        }

        if (!isAnswer) {
            System.out.println("\nError update: Resume " + r.getUuid() + " невозможно обновить, т.к. его нет в архиве!");
        }
    }

    public void save(Resume r) {
        if (count < 10000) {
            for (int i = 0; i < count; i++) {
                if (storage[i].getUuid().equals(r.getUuid())) {
                    System.out.println("\nError save: Resume - " + r.getUuid() + " уже существует в архиве!");
                    isAnswer = true;
                    break;
                }
            }

            if (!isAnswer) {
                System.out.println("Save: Resume - " + r.getUuid() + " добавлено в архив!");
                storage[count] = r;
                count++;
            }
        } else {
            System.out.println("Закончилось свободное место для новых резюме!");
        }
    }

    public Resume get(String uuid) {
        Resume temp = new Resume();
        temp.setUuid("Error! Resume - " + uuid + " не существует!");

        for (int i = 0; i < count; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }

        return temp;
    }

    public void delete(String uuid) {
        int i;

        for (i = 0; i < count; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                System.out.println("\nDelete: Resume - " + uuid + " было удалено из архива!");
                isAnswer = true;
                break;
            }
        }

        if (isAnswer) {
            for (int j = i; j < count - 1; j++) {
                storage[j] = storage[j + 1];
            }
            count--;
        } else {
            System.out.println("\nError delete: Resume - " + uuid + " нет в архиве, невозможно удалить данное резюме!");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, count);
    }

    public int size() {
        return count;
    }
}
