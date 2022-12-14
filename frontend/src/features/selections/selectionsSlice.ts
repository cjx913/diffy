import {createSlice, PayloadAction} from '@reduxjs/toolkit';
interface Selections {
    noiseCancellationIsOn: boolean, // Noise cancellation from AppBarView
    infoIsOpen: boolean, // InfoView dialog
    deleteRequestAlertIsOpen: boolean, // Alert that shows up inside info view when all reqests are deleted
    requestIsOpen: boolean, // RequestView Dialog
    endpointName: string|undefined, // Selected endpoint from EnpointsView
    fieldPrefix: string|undefined, // Selected field prefix from FieldsView
    requestId: string|undefined, // Selected request id from DifferencesView
}
const initialState: Selections = {
    noiseCancellationIsOn: false,
    endpointName: undefined,
    fieldPrefix: undefined,
    requestId: undefined,
    infoIsOpen: false,
    deleteRequestAlertIsOpen: false,
    requestIsOpen: false
};
const slice = createSlice({
    name: 'selections',
    initialState,
    reducers: {
        toggleNoiseCancellation(state){
            state.noiseCancellationIsOn = !state.noiseCancellationIsOn;
        },
        openInfoView(state){
            state.infoIsOpen = true;
        },
        closeInfoView(state){
            state.infoIsOpen = false;
        },
        openDeleteRequestsAlert(state){
            state.deleteRequestAlertIsOpen = true;
        },
        closeDeleteRequestsAlert(state){
            state.deleteRequestAlertIsOpen = false;
        },
        openRequestView(state){
            state.requestIsOpen = true;
        },
        closeRequestView(state){
            state.requestIsOpen = false;
        },
        selectEndpoint(state, endpointName){
            state.endpointName = endpointName.payload;
        },
        selectFieldPrefix(state, fieldPrefix) {
            state.fieldPrefix = fieldPrefix.payload;
        },
        selectRequest(state, requestId) {
            state.requestId = requestId.payload;
        }
    }
})

export const {
    toggleNoiseCancellation,
    openInfoView,
    closeInfoView,
    openDeleteRequestsAlert,
    closeDeleteRequestsAlert,
    openRequestView,
    closeRequestView,
    selectEndpoint,
    selectFieldPrefix,
    selectRequest
} = slice.actions;
export default slice.reducer;