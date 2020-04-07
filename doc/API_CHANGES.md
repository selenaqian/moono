### API Changes

Documents any changes to the API after the finalization of the plan.

1. added drawCard() method in DiscardPile
2. removed Action enum (and all its occurrences)
3. Changed the ViewInterface to GameViewInterface. This new interface now has methods updateHand and updateDiscardPile.
As we figured out more of the details of implementation and information flow, we decided to have a SetupView class that
would store a GameSettings object and call setter methods for each of the sliders instead of having the view package send
that information elsewhere. We also determined that we would need methods to allow the controller package to call updates
on the view when actions in the backend occurred.