package org.example.umc10th.domain.mission.dto;


import org.example.umc10th.domain.mission.enums.MissionFilterStatus;


public class UserMissionRequest{
    public record SortMyMission(

           MissionFilterStatus missionFilterStatus
    ){}
}

