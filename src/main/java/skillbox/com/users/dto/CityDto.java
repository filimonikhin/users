package skillbox.com.users.dto;

public class CityDto implements Cloneable {
    private Integer id;
    private String name;
    //  private List<UserEntity> userEntityList = new ArrayList<>();

    public CityDto() {
        //
    }

    public CityDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CityDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public CityDto clone() {
        try {
            CityDto clone = (CityDto) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
