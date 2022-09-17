package com.github.jaczerob.madamchuckle.toontown.loaders;

import com.github.jaczerob.madamchuckle.toontown.models.ToontownObject;

public interface Loader<T extends ToontownObject> {
    public T load();
}
