package com.marukovich.second_project.entity;

import java.math.BigDecimal;

public abstract class AbstractDevice {

    private String deviceId;
    private String title;
    private String name;
    private String origin;
    private BigDecimal price;
    private String critical;
    private Type type;

    public AbstractDevice(){
        type = new Type();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractDevice that = (AbstractDevice) o;

        if (!deviceId.equals(that.deviceId)) return false;
        if (!title.equals(that.title)) return false;
        if (!name.equals(that.name)) return false;
        if (!origin.equals(that.origin)) return false;
        if (!price.equals(that.price)) return false;
        if (!critical.equals(that.critical)) return false;
        return type.equals(that.type);
    }

    @Override
    public int hashCode() {
        int result = deviceId.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + origin.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + critical.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractDevice{");
        sb.append("deviceId='").append(deviceId).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", origin='").append(origin).append('\'');
        sb.append(", price=").append(price);
        sb.append(", critical='").append(critical).append('\'');
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
