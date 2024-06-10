import axios from "axios";

export const handleAxiosError = (description: string, error: unknown): void => {
    if (axios.isAxiosError(error)) {
      const message = error.response?.data || error.message;
      alert(description + ': \n' + message);
      } else {
        alert('An unexpected error occurred: ' + error);
    }
  };