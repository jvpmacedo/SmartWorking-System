import React, { createContext, useContext, useState } from 'react';
import ErrorMessageModal from '../components/ErrorMessageModal';

const ErrorContext = createContext();

export const useError = () => useContext(ErrorContext);

export const ErrorProvider = ({ children }) => {
  const [errorMessage, setErrorMessage] = useState(null);

  const showError = (message) => {
    setErrorMessage(message);
  };

  const hideError = () => {
    setErrorMessage(null);
  };

  return (
    <ErrorContext.Provider value={{ showError, hideError }}>
      {children}
      <ErrorMessageModal message={errorMessage} onClose={hideError} />
    </ErrorContext.Provider>
  );
};
