package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
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

    @Override
    protected void insertResume(Resume resume, int index) {
        int num = -(index + 1);

        for (int i = size; i > num; i--) {
            storage[i] = storage[i - 1];
        }

        storage[num] = resume;
    }

    @Override
    protected void removeResume(String uuid, int index) {
        for (int i = index; i < size - 1; i++) {
            storage[i] = storage[i + 1];
        }
    }
}
