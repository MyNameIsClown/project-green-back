package project.activities.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ActivityTypes {
    BIODIVERSITY_CONSERVATION("biodiversity conservation"),
    RENEWABLE_ENERGY("renewable energy"),
    WASTE_MANAGEMENT("waste management"),
    ENVIRONMENTAL_EDUCATION("environmental education"),
    DONATIONS("donations");

    @Getter
    private String value;
}
