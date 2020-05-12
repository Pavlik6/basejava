package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index >= 0) {
            storage[index] = resume;
            System.out.println("\nResume: " + resume.getUuid() + " was update!");
        } else {
            System.out.println("\nError: Resume - " + resume.getUuid() + " not in the storage!");
        }
    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (size < AMOUNT) {
            if (index >= 0) {
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

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index >= 0) {
            return storage[index];
        }

        System.out.println("Error: Resume - " + uuid + " not in the storage!");
        return null;
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index >= 0) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            System.out.println("\nResume - " + uuid + " was delete!");
        } else
            System.out.println("Error: Resume - " + uuid + " not in the storage!");
    }

    /**
     * Equals Resumes in the storage
     *
     * @return index of Resume
     */
    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
