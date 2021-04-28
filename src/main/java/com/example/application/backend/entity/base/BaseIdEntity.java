package com.example.application.backend.entity.base;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseIdEntity<T> {
    public BaseIdEntity() {
    }

    public abstract void setId(T id);

    public abstract T getId();

    public int hashCode() {
        return this.getId() != null ? this.getId().hashCode() : 0;
    }

    public String toString() {
        return this.getClass().getName() + "-" + this.getId();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            boolean result;
            label: {
                BaseIdEntity that = (BaseIdEntity) o;
                if (this.getId() != null) {
                    if (this.getId().equals(that.getId())) {
                        break label;
                    }
                } else if (that.getId() == null) {
                    break label;
                }

                result = false;
                return result;
            }

            result = true;
            return result;
        } else {
            return false;
        }
    }
}
