import { registerUser } from '@/utils';
import { useState } from 'react';

const RegistrationForm: React.FC = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [registrationError, setRegistrationError] = useState<string | null>(null);

  const handleRegistration = async () => {
    try {
      registerUser(email, password);
      } catch (error) {
        setRegistrationError('Something went wrong. Please try again.'); // Customize error message
    }
  };

  return (
    <div>
      <input
        type="email"
        placeholder="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      {registrationError && <p>{registrationError}</p>}
      <button onClick={handleRegistration}>Register</button>
    </div>
  );
};

export default RegistrationForm;