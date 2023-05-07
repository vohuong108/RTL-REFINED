-----------------------------------------------------------------------------
--open uml2csp.use
!create rc: RuleCollection 
-------------------------------------------------------Example ActivityDiagrma
!create start:InitialNode

!create s1:ActivityEdge
!create s2:ActivityEdge
!create s1Action:Action
!set s1.name:='S1'
--create action serverReceiveAlert
!set s2.name:='S2'
!set s1Action.name := 'serverReceiveAlert'
!insert (s1,start) into ConnectsFrom
!insert (s1,s1Action) into ConnectsTo
!insert (s2,s1Action) into ConnectsFrom

--create action getDriverPhoneData
!create s2Action:Action
!set s2Action.name := 'getDriverPhoneData'
!create s3:ActivityEdge
!set s3.name := 'S3'
!insert (s2,s2Action) into ConnectsTo
!insert (s3,s2Action) into ConnectsFrom

--create action callDriver
!create s3Action:Action
!set s3Action.name := 'callDriver'
!create s4:ActivityEdge
!set s4.name := 'S4'
!insert (s3,s3Action) into ConnectsTo
!insert (s4,s3Action) into ConnectsFrom

--create decision node
!create s4Decision:DecisionNode
!create m1:ActivityEdge
!create d1a:ActivityEdge
!create d2:ActivityEdge
!set m1.name := 'M1'
!set m1.guard := 'noHelp'
!set d1a.name := 'D1a'
!set d1a.guard := 'else'
!set d2.name := 'D2'
!set d2.guard := 'askHelp'
!insert (s4,s4Decision) into ConnectsTo
!insert (d2,s4Decision) into ConnectsFrom
!insert (d1a,s4Decision) into ConnectsFrom
!insert (m1,s4Decision) into ConnectsFrom

--create mergeNode
!create d2MergeNode:MergeNode
!create d1b:ActivityEdge
!create dm:ActivityEdge
!set d1b.name := 'D1b'
!set dm.name := 'DM'
!insert (d2, d2MergeNode) into ConnectsTo
!insert (d1b,d2MergeNode) into ConnectsTo
!insert (dm,d2MergeNode) into ConnectsFrom

--create d1aAction
!create d1aAction:Action
!set d1aAction.name := 'accessDescription'
!insert (d1a,d1aAction) into ConnectsTo
!insert (d1b,d1aAction) into ConnectsFrom

--create m1MergeNode
!create m1MergeNode:MergeNode
!create m2:ActivityEdge
!create c1:ActivityEdge
!set c1.name := 'C1'
!set m2.name := 'M2'
!set m2.guard := 'else'
!insert (m1,m1MergeNode) into ConnectsTo
!insert (m2,m1MergeNode) into ConnectsTo
!insert (c1,m1MergeNode) into ConnectsFrom

--create c1Action
!create c1Action:Action
!create c2:ActivityEdge
!set c1Action.name := 'cancelAlert'
!set c2.name := 'C2'
!insert (c1, c1Action) into ConnectsTo
!insert (c2, c1Action) into ConnectsFrom

--create finalNode
!create c2FinalNode:FinalNode
!insert (c2, c2FinalNode) into ConnectsTo

--create dmDecision
!create dmDecision:DecisionNode
!create d3:ActivityEdge
!set d3.name := 'D3'
!set d3.guard := 'real'
!insert (dm, dmDecision) into ConnectsTo
!insert (d3, dmDecision) into ConnectsFrom
!insert (m2, dmDecision) into ConnectsFrom

--create d3ForkeNode
!create d3ForkNode:ForkNode
!create f1:ActivityEdge
!create f2:ActivityEdge
!create f3:ActivityEdge
!set f1.name := 'F1'
!set f2.name := 'F2'
!set f3.name := 'F3'
!insert (d3, d3ForkNode) into ConnectsTo
!insert (f1, d3ForkNode) into ConnectsFrom
!insert (f2, d3ForkNode) into ConnectsFrom
!insert (f3, d3ForkNode) into ConnectsFrom

--create f1Action
!create f1Action:Action
!create j1:ActivityEdge
!set f1Action.name := 'getMapLocation'
!set j1.name := 'J1'
!insert (f1, f1Action) into ConnectsTo
!insert (j1, f1Action) into ConnectsFrom

--create f2Action
!create f2Action:Action
!create j2:ActivityEdge
!set f2Action.name := 'processAlert'
!set j2.name := 'J2'
!insert (f2, f2Action) into ConnectsTo
!insert (j2, f2Action) into ConnectsFrom

--create f3Action
!create f3Action:Action
!create j3:ActivityEdge
!set f3Action.name := 'getServiceFormat'
!set j3.name := 'J3'
!insert (f3, f3Action) into ConnectsTo
!insert (j3, f3Action) into ConnectsFrom

--create j1joinNode
!create j1JoinNode:JoinNode
!create e1:ActivityEdge
!set e1.name := 'E1'
!insert (j1, j1JoinNode) into ConnectsTo
!insert (j2, j1JoinNode) into ConnectsTo
!insert (j3, j1JoinNode) into ConnectsTo
!insert (e1, j1JoinNode) into ConnectsFrom

--create e1Action
!create e1Action:Action
!create e2:ActivityEdge
!set e1Action.name := 'createServiceDescription'
!set e2.name := 'E2'
!insert (e1, e1Action) into ConnectsTo
!insert (e2, e1Action) into ConnectsFrom

--create e2FinalNode
!create e2FinalNode:FinalNode
!insert (e2, e2FinalNode) into ConnectsTo
-------------------------------------------------------Example CSP Model
--start
!create process1:Process
!set process1.name:='S1'

--create action serverReceiveAlert
!create assignment1:ProcessAssignment
!create event1:Event
!create prefix1:Prefix
!create process2:Process
!set process2.name:='S2'
!set event1.name:='serverReceiveAlert'
!insert (assignment1, process1) into AssignsLeft
!insert (assignment1, prefix1) into AssignsRight
!insert (prefix1, event1) into Performs
!insert (prefix1, process2) into Becomes

--create action getDriverPhoneData
!create assignment2:ProcessAssignment
!create event2:Event
!create prefix2:Prefix
!create process3:Process
!set process3.name:='S3'
!set event2.name:='getDriverPhoneData'
!insert (assignment2, process2) into AssignsLeft
!insert (assignment2, prefix2) into AssignsRight
!insert (prefix2, event2) into Performs
!insert (prefix2, process3) into Becomes

--create action callDriver
!create assignment3:ProcessAssignment
!create event3:Event
!create prefix3:Prefix
!create process4:Process
!set process4.name:='S4'
!set event3.name:='callDriver'
!insert (assignment3, process3) into AssignsLeft
!insert (assignment3, prefix3) into AssignsRight
!insert (prefix3, event3) into Performs
!insert (prefix3, process4) into Becomes

--create decision node
!create assignment4:ProcessAssignment
!create process5:Process
!create condition1:Condition
!create condition2:Condition
!create process6:Process
!create process7:Process
!set condition1.expression:='noHelp'
!set condition2.expression:='askHelp'
!set process5.name:='M1'
!set process6.name:='D2'
!set process7.name:='D1a'
!insert (assignment4, process4) into AssignsLeft
!insert (assignment4, condition1) into AssignsRight
!insert (condition2, process6) into CombinesLeft
!insert (condition2, process7) into CombinesRight
!insert (condition1, condition2) into CombinesRight
!insert (condition1, process5) into CombinesLeft

--create d1aAction
!create assignment5:ProcessAssignment
!create event4:Event
!create prefix4:Prefix
!create process8:Process
!set process8.name:='D1b'
!set event4.name:='accessDescription'
!insert (assignment5, process7) into AssignsLeft
!insert (assignment5, prefix4) into AssignsRight
!insert (prefix4, event4) into Performs
!insert (prefix4, process8) into Becomes

--create mergeNode
!create assignment6:ProcessAssignment
!create assignment7:ProcessAssignment
!create process9:Process
!set process9.name:='DM'
!insert (assignment6, process6) into AssignsLeft
!insert (assignment6, process9) into AssignsRight
!insert (assignment7, process8) into AssignsLeft
!insert (assignment7, process9) into AssignsRight

--create dmDecision
!create assignment8:ProcessAssignment
!create condition3:Condition
!create process10:Process
!create process11:Process
!set condition3.expression:='real'
!set process10.name:='D3'
!set process11.name:='M2'
!insert (assignment8, process9) into AssignsLeft
!insert (assignment8, condition3) into AssignsRight
!insert (condition3, process10) into CombinesLeft
!insert (condition3, process11) into CombinesRight

--create m1MergeNode
!create assignment9:ProcessAssignment
!create assignment10:ProcessAssignment
!create process12:Process
!set process12.name:='C1'
!insert (assignment9, process5) into AssignsLeft
!insert (assignment9, process12) into AssignsRight
!insert (assignment10, process11) into AssignsLeft
!insert (assignment10, process12) into AssignsRight

--create c1Action
!create assignment20:ProcessAssignment
!create event10:Event
!create prefix12:Prefix
!create process23:Process
!set process23.name:='C2'
!set event10.name:='cancelAlert'
!insert (assignment20, process12) into AssignsLeft
!insert (assignment20, prefix12) into AssignsRight
!insert (prefix12, event10) into Performs
!insert (prefix12, process23) into Becomes

--create finalNode
!create assignment21:ProcessAssignment
!create process24:Process
!set process24.name:='SKIP'
!insert (assignment21, process23) into AssignsLeft
!insert (assignment21, process24) into AssignsRight

--create d3ForkeNode
!create assignment11:ProcessAssignment
!create concurrency1:Concurrency
!create concurrency2:Concurrency
!create process13:Process
!create process14:Process
!create process15:Process
!set process13.name:='F1'
!set process14.name:='F2'
!set process15.name:='F3'
!insert (assignment11, process10) into AssignsLeft
!insert (assignment11, concurrency1) into AssignsRight
!insert (concurrency1, concurrency2) into CombinesRight
!insert (concurrency1, process13) into CombinesLeft
!insert (concurrency2, process14) into CombinesLeft
!insert (concurrency2, process15) into CombinesRight

--create f1Action
!create assignment12:ProcessAssignment
!create event5:Event
!create prefix5:Prefix
!create process16:Process
!set process16.name:='J1'
!set event5.name:='getMapLocation'
!insert (assignment12, process13) into AssignsLeft
!insert (assignment12, prefix5) into AssignsRight
!insert (prefix5, event5) into Performs
!insert (prefix5, process16) into Becomes

--create f2Action
!create assignment13:ProcessAssignment
!create event6:Event
!create prefix6:Prefix
!create process17:Process
!set process17.name:='J2'
!set event6.name:='processAlert'
!insert (assignment13, process14) into AssignsLeft
!insert (assignment13, prefix6) into AssignsRight
!insert (prefix6, event6) into Performs
!insert (prefix6, process17) into Becomes

--create f3Action
!create assignment14:ProcessAssignment
!create event7:Event
!create prefix7:Prefix
!create process18:Process
!set process18.name:='J3'
!set event7.name:='getServiceFormat'
!insert (assignment14, process15) into AssignsLeft
!insert (assignment14, prefix7) into AssignsRight
!insert (prefix7, event7) into Performs
!insert (prefix7, process18) into Becomes

--create j1joinNode
!create assignment15:ProcessAssignment
!create assignment16:ProcessAssignment
!create assignment17:ProcessAssignment
!create process19:Process
!create process20:Process
!create prefix8:Prefix
!create prefix9:Prefix
!create prefix10:Prefix
!create event8:Event
!set process19.name:='SKIP'
!set process20.name:='E1'
!set event8.name:='processJoin'
!insert (assignment15, process16) into AssignsLeft
!insert (assignment16, process17) into AssignsLeft
!insert (assignment17, process18) into AssignsLeft
!insert (assignment15, prefix8) into AssignsRight
!insert (assignment16, prefix9) into AssignsRight
!insert (assignment17, prefix10) into AssignsRight
!insert (prefix8, event8) into Performs
!insert (prefix9, event8) into Performs
!insert (prefix10, event8) into Performs
!insert (prefix8, process20) into Becomes
!insert (prefix9, process19) into Becomes
!insert (prefix10, process19) into Becomes

--create e1Action
!create assignment18:ProcessAssignment
!create event9:Event
!create prefix11:Prefix
!create process21:Process
!set process21.name:='E2'
!set event9.name:='createServiceDescription'
!insert (assignment18, process20) into AssignsLeft
!insert (assignment18, prefix11) into AssignsRight
!insert (prefix11, event9) into Performs
!insert (prefix11, process21) into Becomes

--create e2FinalNode

!create assignment19:ProcessAssignment
!create process22:Process
!set process22.name:='SKIP'
!insert (assignment19, process21) into AssignsLeft
!insert (assignment19, process22) into AssignsRight
