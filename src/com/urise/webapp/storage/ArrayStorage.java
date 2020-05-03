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
        if (equalsResume(r)) {
            System.out.println("\nResume: " + r.getUuid() + " was update!");
        } else {
            System.out.println("\nError: Resume - " + r.getUuid() + " isn't in the storage!");
        }
    }

    public void save(Resume r) {
        if (size < AMOUNT) {
            if (equalsResume(r)) {
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
        return equalsResume(uuid);
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                System.out.println("\nResume - " + uuid + " was delete!");
            }
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
    private boolean equalsResume(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(resume.getUuid())) {
                return true;
            }
        }

        return false;
    }

    //Equals resumes strings in the storage
    private Resume equalsResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }

        System.out.println("\nError: Resume - " + uuid + " isn't in the storage");
        return null;
    }
}

