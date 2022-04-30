package com.noahbres.meepmeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.DriveTrainType;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        // Declare a MeepMeep instance
        // With a field size of 800 pixels
        MeepMeep meepMeep = new MeepMeep(800);

        Vector2d vector = new Vector2d(-11.6, 42);
        Pose2d startPose = new Pose2d(vector, Math.toRadians(-270));

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setDimensions(13, 18)
                // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 13.75)
                .setStartPose(startPose)
                // Option: Set theme. Default = ColorSchemeRedDark()
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(drive -> {
                            return drive.trajectorySequenceBuilder(startPose)
                                    .splineToSplineHeading(new Pose2d(14, 63.5, Math.toRadians(0)), 0)
                                    .lineTo(new Vector2d(54, 63.5))
                                    .lineTo(new Vector2d(14, 63.5))
                                    .splineToSplineHeading(startPose, Math.toRadians(270))
//                                    .splineToSplineHeading(new Pose2d(57.5, 63.5, Math.toRadians(0)), 0)

//                                    .splineToLinearHeading(new Pose2d(22.7, 62.9, Math.toRadians(0)), Math.toRadians(-180))
//                                    .splineTo(new Vector2d(32, -21), 0)
//                                    .splineTo(new Vector2d(35, -10), Math.toRadians(270))
                                    .build();
                        }
                );


        meepMeep.setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
                .setDarkMode(true)
                // Background opacity from 0-1
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}