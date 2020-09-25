package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int AMOUNT = 10000;
    protected Resume[] storage = new Resume[AMOUNT];
    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index >= 0) {
            storage[index] = resume;
            System.out.println("\nResume: " + resume.getUuid() + " was update!");
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (size < AMOUNT) {
            if (index >= 0) {
                throw new ExistStorageException(resume.getUuid());
            } else {
                insertResume(resume, index);
                System.out.println("Resume - " + resume.getUuid() + " was saved in the storage!");
                size++;
            }
        } else {
            throw new StorageException("Out of space for new resumes!", resume.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index >= 0) {
            return storage[index];
        }

        throw new NotExistStorageException(uuid);
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index >= 0) {
            removeResume(uuid, index);
            storage[size - 1] = null;
            size--;
            System.out.println("\nResume - " + uuid + " was delete!");
        } else
            throw new NotExistStorageException(uuid);
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }


    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(Resume resume, int index);

    protected abstract void removeResume(String uuid, int index);
}
