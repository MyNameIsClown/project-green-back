package project.carbonFootprint.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name="carbonfootprintcalcparameters")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Log
public class CarbonFootprintCalcParameter {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false)
        private String category;

        @Column(nullable = false)
        private double co2Factor;

        @Column(nullable = false)
        private double efficiencyFactor;

        @Column(nullable = false)
        private String recipe;

        public static boolean isUnipersonalTransport(String type){
            return List.of("gasoline", "diesel", "gnc", "glp").contains(type);
        }

        public static boolean isPublicTransport(String type){
                return List.of("plane", "ship", "train").contains(type);
        }

        public static boolean isLongTravelTransport(String type){
                return List.of("bus", "subway", "commuter_train", "tram", "taxi").contains(type);
        }

}
