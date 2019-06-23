package io.spotnext.kakao.support;

import ca.weblite.objc.Proxy;
import ca.weblite.objc.annotations.Msg;
import io.spotnext.kakao.annotations.Optional;

public interface NSOutlineViewDataSource {

	/**
	 * 
	 * @param outlineView
	 * @param item
	 * @return NSInteger
	 */
	@Msg(selector = "outlineView:numberOfChildrenOfItem:", signature = "l@:@:@")
	int outlineViewNumberOfChildrenOfItem(Proxy outlineView, Proxy item);

	/**
	 * 
	 * @param outlineView NSOutlineView
	 * @param index       NSInteger
	 * @param item
	 * @return
	 */
	@Msg(selector = "outlineView:child:ofItem:", signature = "@@:@:@:@")
	Proxy outlineViewChildOfItem(Proxy outlineView, int index, Proxy item);

	/**
	 * 
	 * @param outlineView NSOutlineView
	 * @param item
	 * @return
	 */
	@Msg(selector = "outlineView:isItemExpandable:", signature = "@@:@:@")
	boolean outlineViewIsItemExpandable(Proxy outlineView, Proxy item);

	/**
	 * NOTE: This method is optional for the View Based OutlineView.
	 * 
	 * @param outlineView NSOutlineView
	 * @param tableColumn NSTableColumn
	 * @param item
	 */
	@Msg(selector = "outlineView:objectValueForTableColumn:byItem:", signature = "@@:@:@:@")
	Proxy outlineViewObjectValueForTableColumnByItem(Proxy outlineView, Proxy tableColumn, Proxy item);

	/**
	 * NOTE: View Based OutlineView: This method is not applicable.
	 * 
	 * @param outlineView NSOutlineView
	 * @param item
	 * @param tableColumn NSTableColumn
	 * @param item
	 */
	@Optional
	@Msg(selector = "outlineView:setObjectValue:forTableColumn:byItem:")
	default void outlineViewSetObjectValueForTableColumnByItem(Proxy outlineView, Proxy object, Proxy tableColumn,
			Proxy item) {

	}

	/**
	 * NOTE: Returning nil indicates the item no longer exists, and won't be
	 * re-expanded.
	 * 
	 * @param outlineView NSOutlineView
	 * @param item
	 */
	@Optional
	@Msg(selector = "outlineView:itemForPersistentObject:")
	default Proxy outlineViewItemForPersistentObject(Proxy outlineView, Object object) {
		return null;
	}

	/**
	 * NOTE: Returning nil indicates that the item's state will not be persisted.
	 * 
	 * @param outlineView NSOutlineView
	 * @param item
	 */
	@Optional
	@Msg(selector = "outlineView:persistentObjectForItem:")
	default Proxy outlineViewPersistentObjectForItem(Proxy outlineView, Proxy item) {
		return null;
	}

	/**
	 * Optional - Sorting Support This is the indication that sorting needs to be
	 * done. Typically the data source will sort its data, reload, and adjust
	 * selections.
	 * 
	 * @param outlineView    NSOutlineView
	 * @param oldDescriptors NSArray<NSSortDescriptor>
	 */
	@Optional
	@Msg(selector = "outlineView:sortDescriptorsDidChange:")
	default void outlineViewSortDescriptorsDidChangeOldDescriptors(Proxy outlineView, Proxy oldDescriptors) {

	}

	/**
	 * Optional - Drag and Drop support
	 */

	/**
	 * Dragging Source Support - Required for multi-image dragging. Implement this
	 * method to allow the table to be an NSDraggingSource that supports multiple
	 * item dragging. Return a custom object that implements NSPasteboardWriting (or
	 * simply use NSPasteboardItem). Return nil to prevent a particular item from
	 * being dragged. If this method is implemented, then
	 * outlineView:writeItems:toPasteboard: will not be called.
	 * 
	 * @param outlineView NSOutlineView
	 * @param item
	 * 
	 * @return NSPasteboardWriting
	 */
	@Optional
	@Msg(selector = "outlineView:pasteboardWriterForItem:")
	default Proxy outlineViewPasteboardWriterForItem(Proxy outlineView, Proxy item) {
		return null;
	}

	/**
	 * Dragging Source Support - Optional. Implement this method know when the
	 * dragging session is about to begin and to potentially modify the dragging
	 * session. 'draggedItems' is an array of items that we dragged, excluding items
	 * that were not dragged due to outlineView:pasteboardWriterForItem: returning
	 * nil. This array will directly match the pasteboard writer array used to begin
	 * the dragging session with [NSView
	 * beginDraggingSessionWithItems:event:source]. Hence, the order is
	 * deterministic, and can be used in -outlineView:acceptDrop:item:childIndex:
	 * when enumerating the NSDraggingInfo's pasteboard classes.
	 * 
	 * @param outlineView  NSOutlineView
	 * @param session      NSDraggingSession
	 * @param screenPoint  NSPoint
	 * @param draggedItems NSArray
	 */
	@Optional
	@Msg(selector = "outlineView:draggingSession:willBeginAtPoint:forItems:")
	default void outlineViewDraggingSessionWillBeginAtPointForItems(Proxy outlineView, Proxy session, Proxy screenPoint,
			Proxy draggedItems) {

	}

	/**
	 * Dragging Source Support - Optional. Implement this method know when the
	 * dragging session has ended. This delegate method can be used to know when the
	 * dragging source operation ended at a specific location, such as the trash (by
	 * checking for an operation of NSDragOperationDelete).
	 * 
	 * @param outlineView NSOutlineView
	 * @param session     NSDraggingSession
	 * @param screenPoint NSPoint
	 * @param operation   NSDragOperation
	 */
	@Optional
	@Msg(selector = "outlineView:draggingSession:endedAtPoint:operation:")
	default void outlineViewDraggingSessionEndedAtPointOperation(Proxy outlineView, Proxy session, Proxy screenPoint,
			Proxy operation) {

	}

	/**
	 * Dragging Source Support - Optional for single-image dragging. This method is
	 * called after it has been determined that a drag should begin, but before the
	 * drag has been started. To refuse the drag, return NO. To start a drag, return
	 * YES and place the drag data onto the pasteboard (data, owner, etc...). The
	 * drag image and other drag related information will be set up and provided by
	 * the outline view once this call returns with YES. The items array is the list
	 * of items that will be participating in the drag.
	 * 
	 * @param outlineView NSOutlineView
	 * @param items       NSArray
	 * @param pasteboard
	 * @return NSPasteboard
	 */
	@Optional
	@Msg(selector = "outlineView:writeItems:toPasteboard:")
	default boolean outlineViewWriteItemsToPasteboard(Proxy outlineView, Proxy items, Proxy pasteboard) {
		return false;
	}

	/**
	 * Dragging Destination Support - Required for multi-image dragging. Implement
	 * this method to allow the table to update dragging items as they are dragged
	 * over the view. Typically this will involve calling [draggingInfo
	 * enumerateDraggingItemsWithOptions:forView:classes:searchOptions:usingBlock:]
	 * and setting the draggingItem's imageComponentsProvider to a proper image
	 * based on the content. For View Based TableViews, one can use
	 * NSTableCellView's -draggingImageComponents and -draggingImageFrame.
	 * 
	 * @param outlineView  NSOutlineView
	 * @param draggingInfo NSDraggingInfo
	 */
	@Optional
	@Msg(selector = "outlineView:updateDraggingItemsForDrag:")
	default void outlineViewUpdateDraggingItemsForDrag(Proxy outlineView, Proxy draggingInfo) {

	}

	/**
	 * Dragging Destination Support - This method is used by NSOutlineView to
	 * determine a valid drop target. Based on the mouse position, the outline view
	 * will suggest a proposed child 'index' for the drop to happen as a child of
	 * 'item'. This method must return a value that indicates which NSDragOperation
	 * the data source will perform. The data source may "re-target" a drop, if
	 * desired, by calling setDropItem:dropChildIndex: and returning something other
	 * than NSDragOperationNone. One may choose to re-target for various reasons
	 * (eg. for better visual feedback when inserting into a sorted position). On
	 * Leopard linked applications, this method is called only when the drag
	 * position changes or the dragOperation changes (ie: a modifier key is
	 * pressed). Prior to Leopard, it would be called constantly in a timer,
	 * regardless of attribute changes.
	 * 
	 * @return NSDragOperation
	 * 
	 * @param outlineView NSOutlineView
	 * @param info        NSDraggingInfo
	 * @param item
	 * @param index       NSInteger
	 * @return
	 */
	@Optional
	@Msg(selector = "outlineView:validateDrop:proposedItem:proposedChildIndex:")
	default Proxy outlineViewValidateDropProposedItemProposedChildIndex(Proxy outlineView, Proxy info, Proxy item,
			int index) {
		return null;
	}

	/**
	 * Dragging Destination Support - This method is called when the mouse is
	 * released over an outline view that previously decided to allow a drop via the
	 * validateDrop method. The data source should incorporate the data from the
	 * dragging pasteboard at this time. 'index' is the location to insert the data
	 * as a child of 'item', and are the values previously set in the validateDrop:
	 * method.
	 * 
	 * @param outlineView NSOutlineView
	 * @param info        NSDraggingInfo
	 * @param item
	 * @param index       NSInteger
	 * @return
	 */
	@Optional
	@Msg(selector = "outlineView:acceptDrop:item:childIndex:")
	default boolean outlineViewAcceptDropItemChildIndex(Proxy outlineView, Proxy info, Proxy item, int index) {
		return false;
	}

	/**
	 * Dragging Destination Support - NSOutlineView data source objects can support
	 * file promised drags via by adding NSFilesPromisePboardType to the pasteboard
	 * in outlineView:writeItems:toPasteboard:. NSOutlineView implements
	 * -namesOfPromisedFilesDroppedAtDestination: to return the results of this data
	 * source method. This method should returns an array of filenames for the
	 * created files (filenames only, not full paths). The URL represents the drop
	 * location. For more information on file promise dragging, see documentation on
	 * the NSDraggingSource protocol and -namesOfPromisedFilesDroppedAtDestination:.
	 * 
	 * @param outlineView     NSOutlineView
	 * @param dropDestination NSURL
	 * @param items           NSURL
	 * @return NSArray<NSString>
	 */
	@Optional
	@Msg(selector = "outlineView:namesOfPromisedFilesDroppedAtDestination:forDraggedItems:")
	default Proxy outlineViewNamesOfPromisedFilesDroppedAtDestinationForDraggedItems(Proxy outlineView,
			Proxy dropDestination, Proxy items) {
		return null;
	}

}
