-- sample with an inconsistency: event.name <> action.name
!create rc: RuleCollection 
-------------------------------------------------------Example ActivityDiagram
!create start:InitialNode
!create s1:ActivityEdge
!set s1.name:='S1'
!insert (s1,start) into ConnectsFrom

!create s2:ActivityEdge
!set s2.name:='S2'
!create s1Action:Action
!set s1Action.name:='serverReceiveAlert'
!insert (s1,s1Action) into ConnectsTo
!insert (s2,s1Action) into ConnectsFrom

-------------------------------------------------------Example CSP Model
!create process1:Process
!set process1.name:='S1'

!create assignment1:ProcessAssignment
!create event1:Event
!create prefix1:Prefix
!create process2:Process
!set process2.name:='S2'


--inconsistency
!set event1.name:='serverReceiveAlert1'
!insert (assignment1, process1) into AssignsLeft
!insert (assignment1, prefix1) into AssignsRight
!insert (prefix1, event1) into Performs
!insert (prefix1, process2) into Becomes
