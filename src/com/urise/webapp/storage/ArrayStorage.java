package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int AMOUNT = 10000;
    private Resume[] storage = new Resume[AMOUNT];
    private int size = 0;
    private int num;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        num = equalsResume(resume.getUuid());

        if (num != -1) {
            storage[num] = resume;
            System.out.println("\nResume: " + resume.getUuid() + " was update!");
        } else {
            System.out.println("\nError: Resume - " + resume.getUuid() + " not in the storage!");
        }
    }

    public void save(Resume resume) {
        num = equalsResume(resume.getUuid());

        if (size < AMOUNT) {
            if (num != -1) {
                System.out.println("Error: Resume - " + resume.getUuid() + " had in the storage yet!");
            } else {
                storage[size] = resume;
                size++;
                System.out.println("Resume - " + resume.getUuid() + " was saved in the storage!");
            }
        } else {
            System.out.println("Out of space for new resumes!");
        }
    }

    public Resume get(String uuid) {
        num = equalsResume(uuid);

        if (num != -1) {
            return storage[num];
        }

        System.out.println("Error: Resume - " + uuid + " not in the storage!");
        return null;
    }

    public void delete(String uuid) {
        num = equalsResume(uuid);

        if (num != -1) {
            storage[num] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            System.out.println("\nResume - " + uuid + " was delete!");
        } else
            System.out.println("Error: Resume - " + uuid + " not in the storage!");
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

    /**
     * Equals Resumes in the storage
     *
     * @return index of Resume
     */
    private int equalsResume(String uuid) {
        int i;

        for (i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }

        return -1;
    }
}

