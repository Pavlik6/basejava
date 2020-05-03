package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int AMOUNT = 10000;
    private Resume[] storage = new Resume[AMOUNT];
    private static int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        if (equalsResume(r.getUuid()) != null) {
            System.out.println("\nResume: " + r.getUuid() + " was update!");
        } else {
            System.out.println("\nError: Resume - " + r.getUuid() + " not in the storage!");
        }
    }

    public void save(Resume r) {
        if (size < AMOUNT) {
            if (equalsResume(r.getUuid()) != null) {
                System.out.println("Error: Resume - " + r.getUuid() + " had in the storage yet!");
            } else {
                storage[size] = r;
                size++;
                System.out.println("Resume - " + r.getUuid() + " was saved in the storage!");
            }
        } else {
            System.out.println("Закончилось свободное место для новых резюме!");
        }
    }

    public Resume get(String uuid) {
        if (equalsResume(uuid) != null) {
            return equalsResume(uuid);
        }

        System.out.println("Error: Resume - " + uuid + " not in the storage!");
        return null;
    }

    public void delete(String uuid) {
        if (equalsResume(uuid) != null) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    size--;
                    System.out.println("\nResume - " + uuid + " was delete!");
                }
            }
        } else {
            System.out.println("Error: Resume - " + uuid + " not in the storage!");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    //Equals resumes in the storage
    private Resume equalsResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }

        return null;
    }
}

